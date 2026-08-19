package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.JobDto;
import com.example.byggforetag.DTO.JobItemDto;
import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.*;
import com.example.byggforetag.Repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;


    public JobService(JobRepository jobRepository, UserRepository userRepository, ServiceTypeRepository serviceTypeRepository, ConversationRepository conversationRepository, ConversationParticipantRepository conversationParticipantRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.conversationRepository = conversationRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public JobDto createJob(Long userId, JobDto jobDto){
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

         Job job = new Job(new ArrayList<>(), new ArrayList<>(), user, JobStatus.RECEIVED, jobDto.getAddress(), jobDto.getScheduledDate());
         jobRepository.save(job);

        Conversation conversation = new Conversation(job, "Jobb: " + job.getId());
        conversationRepository.save(conversation);

        ConversationParticipant conversationParticipant = new ConversationParticipant(conversation, user);
        conversationParticipantRepository.save(conversationParticipant);


         List<JobItem> jobItems = new ArrayList<>(jobDto.getJobItem().stream()
                 .map(jobItemDto -> {
                     ServiceType serviceType = serviceTypeRepository.findById(jobItemDto.getServiceTypeId())
                             .orElseThrow(() -> new RuntimeException("ServiceType hittades inte"));
                     return jobItemDto.toEntity(serviceType, job);
                 })
                 .toList());
         job.setJobItems(jobItems);

         return JobDto.fromEntity(jobRepository.save(job));
    }

    public List<JobDto> getJobsByUserId(Long userId){
        List<Job> jobs = jobRepository.findByUserId(userId);
        return jobs.stream()
                .map(JobDto::fromEntity)
                .toList();
    }

    public JobDto getJobById(Long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        return JobDto.fromEntity(job);
    }

    public void deleteJob(Long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        jobRepository.delete(job);
    }

    public List<JobDto> getJobsByEmployeeId(Long employeeId){
        List<Job> jobs = jobRepository.findJobsByEmployeeId(employeeId);
        if (jobs.isEmpty()){
            throw new JobNotFoundException(employeeId);
        }
        return jobs.stream()
                .map(JobDto::fromEntity)
                .toList();
    }

    public List<JobDto> getAllJobs(){
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(JobDto::fromEntity)
                .toList();
    }

    public JobDto updateJobStatus(Long id, JobStatus jobStatus){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        job.setJobStatus(jobStatus);
        return JobDto.fromEntity(jobRepository.save(job));
    }


}
