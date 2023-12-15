package com.edu.iftm.cassandraredis.repository;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.edu.iftm.cassandraredis.model.Imovel;

import java.util.UUID;

@Repository
public interface ImovelRepository extends CassandraRepository<Imovel, UUID> {
}
