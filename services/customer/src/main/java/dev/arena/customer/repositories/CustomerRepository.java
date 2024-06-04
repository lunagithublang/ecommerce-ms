package dev.arena.customer.repositories;

import dev.arena.customer.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CustomerRepository extends MongoRepository <Customer, String> {}
