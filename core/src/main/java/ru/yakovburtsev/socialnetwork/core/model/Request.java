package ru.yakovburtsev.socialnetwork.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class represent a request to add a friend.
 */
@Entity
@Table(name = "requests")
public class Request implements Serializable {
    @Id
    @SequenceGenerator(name = "requests_seq", sequenceName = "requests_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requests_seq")
    @Access(value = AccessType.PROPERTY)
    private Long id;

    @Column(name = "from_user_id", nullable = false)
    private Long fromUserId;

    @Column(name = "to_user_id", nullable = false)
    private Long toUserId;

    public Request() {
    }

    public Request(Long fromUserId, Long toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public Request(Long id, Long fromUserId, Long toUserId) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                '}';
    }
}
