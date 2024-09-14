package com.example.thawaq.Service;

import com.example.thawaq.Api.ApiException;
import com.example.thawaq.Model.Expert;
import com.example.thawaq.Model.Request;
import com.example.thawaq.Repository.ExpertRepository;
import com.example.thawaq.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertRepository expertRepository;
    private final RequestRepository requestRepository;

    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    public Expert getExpertById(Integer id) {
        return expertRepository.findExpertById(id);
    }

    public void approveRequest(Integer expertId, Integer requestId) {
        Expert expert = expertRepository.findExpertById(expertId);
        if (expert == null) {
            throw new ApiException("Expert not found");
        }
        Request request = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new ApiException("Request not found");
        }
        if (!request.getExpert().equals(expert)) {
            throw new ApiException("Request does not beloved to expert");
        }
        request.setStatus(Request.Status.APPROVED);
        requestRepository.save(request);
    }
    public void rejectRequest(Integer expertId, Integer requestId) {
        Expert expert = expertRepository.findExpertById(expertId);
        if (expert == null) {
            throw new ApiException("Expert not found");
        }
        Request request = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new ApiException("Request not found");
        }
        if (!request.getExpert().equals(expert)) {
            throw new ApiException("Request does not beloved to expert");
        }
        request.setStatus(Request.Status.REJECTED);
        requestRepository.save(request);
    }
}
