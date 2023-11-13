package com.example.notificationconsumer.repository;

import com.example.notificationconsumer.entity.Notification;
import org.springframework.data.couchbase.core.query.N1qlJoin;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CouchbaseRepository<Notification,Long> {
}
