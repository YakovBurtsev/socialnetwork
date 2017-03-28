package ru.yakovburtsev.socialnetwork.friends.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;
import ru.yakovburtsev.socialnetwork.friends.repository.RequestRepository;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    @Autowired
    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Request create(Request request) {
        log.info("create {}", request);
        notNull(request, "request must not be null");
        return repository.save(request);
    }

    @Override
    public Request get(Long id) {
        log.info("get request id={}", id);
        Request request = repository.get(id);
        log.info("got: {}", request);
        return request;
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public List<Request> getSentRequests(Long userId) {
        return repository.getSentRequests(userId);
    }

    @Override
    public List<Request> getReceivedRequests(Long userId) {
        return repository.getReceiveRequests(userId);
    }

    @Override
    public boolean isSent(Long fromId, Long toId) {
        return repository.isSent(fromId, toId);
    }
}
