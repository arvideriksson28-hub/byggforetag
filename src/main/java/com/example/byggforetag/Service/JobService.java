package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.JobRequestDto;
import com.example.byggforetag.DTO.JobResponseDto;
import com.example.byggforetag.Enums.JobStatus;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Exception.ServiceTypeNotFoundException;
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

    public JobResponseDto createJob(Long userId, JobRequestDto jobRequestDto){
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

         Job job = jobRequestDto.toEntity(user);
         jobRepository.save(job);

        Conversation conversation = new Conversation(job, "Jobb: " + job.getId());
        conversationRepository.save(conversation);

        ConversationParticipant conversationParticipant = new ConversationParticipant(conversation, user);
        conversationParticipantRepository.save(conversationParticipant);


         List<JobItem> jobItems = new ArrayList<>(jobRequestDto.getJobItem().stream()
                 .map(jobItemDto -> {
                     ServiceType serviceType = serviceTypeRepository.findById(jobItemDto.getServiceTypeId())
                             .orElseThrow(() -> new ServiceTypeNotFoundException(jobItemDto.getServiceTypeId()));
                     return jobItemDto.toEntity(serviceType, job);
                 })
                 .toList());
         job.setJobItems(jobItems);

         return JobResponseDto.fromEntity(jobRepository.save(job));
    }

    public List<JobResponseDto> getJobsByUserId(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        List<Job> jobs = jobRepository.findByUserId(user.getId());
        return jobs.stream()
                .map(JobResponseDto::fromEntity)
                .toList();
    }

    public JobResponseDto getJobById(Long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        return JobResponseDto.fromEntity(job);
    }

    public void deleteJob(Long id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        jobRepository.delete(job);
    }

    public List<JobResponseDto> getJobsByEmployeeId(Long employeeId){
        List<Job> jobs = jobRepository.findJobsByEmployeeId(employeeId);
        if (jobs.isEmpty()){
            throw new JobNotFoundException(employeeId);
        }
        return jobs.stream()
                .map(JobResponseDto::fromEntity)
                .toList();
    }

    public List<JobResponseDto> getAllJobs(){
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(JobResponseDto::fromEntity)
                .toList();
    }

    public JobResponseDto updateJobStatus(Long id, JobStatus jobStatus){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        job.setJobStatus(jobStatus);
        return JobResponseDto.fromEntity(jobRepository.save(job));
    }


}
