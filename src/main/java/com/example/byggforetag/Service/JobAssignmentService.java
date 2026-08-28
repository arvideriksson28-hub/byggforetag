package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.JobAssignmentDto;
import com.example.byggforetag.Exception.EmployeeNotFoundException;
import com.example.byggforetag.Exception.JobNotFoundException;
import com.example.byggforetag.Model.*;
import com.example.byggforetag.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAssignmentService {
    private final JobAssignmentRepository jobAssignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;


    public JobAssignmentService(JobAssignmentRepository jobAssignmentRepository, EmployeeRepository employeeRepository, JobRepository jobRepository, ConversationRepository conversationRepository, ConversationParticipantRepository conversationParticipantRepository) {
        this.jobAssignmentRepository = jobAssignmentRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.conversationRepository = conversationRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public List<JobAssignmentDto> getJobAssignmentByJobId(Long id){
        return jobAssignmentRepository.findAllByJobId(id).stream()
                .map(JobAssignmentDto::fromEntity)
                .toList();
    }

    public JobAssignmentDto assignEmployee(JobAssignmentDto jobAssignmentDto){
        Employee employee = employeeRepository.findById(jobAssignmentDto.getEmployeeId())
                .orElseThrow(()-> new EmployeeNotFoundException(jobAssignmentDto.getEmployeeId()));
        Job job = jobRepository.findById(jobAssignmentDto.getJobId())
                .orElseThrow(()-> new JobNotFoundException(jobAssignmentDto.getJobId()));

        //när anställd tilldelas jobb läggs den även till i konversationen för det jobbet
        User user = employee.getUser();
        Conversation conversation = conversationRepository.findConversationByJobId(job.getId())
                .orElseThrow(()-> new JobNotFoundException(job.getId()));

        ConversationParticipant conversationParticipant = new ConversationParticipant(conversation,user);
        conversationParticipantRepository.save(conversationParticipant);

        JobAssignment saved = jobAssignmentRepository.save(jobAssignmentDto.toEntity(employee, job));
        return JobAssignmentDto.fromEntity(saved);
    }

    public List<JobAssignmentDto> getAllJobAssignmentsByEmployeeId(String email){
        return jobAssignmentRepository.findAllByEmployeeEmail(email).stream()
                .map(JobAssignmentDto::fromEntity)
                .toList();
    }

    public void removeEmployee(Long jobAssignmentId){
        jobAssignmentRepository.deleteById(jobAssignmentId);
    }
}
