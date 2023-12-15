package com.edu.iftm.cassandraredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.edu.iftm.cassandraredis.model.Imovel;
import com.edu.iftm.cassandraredis.repository.ImovelRepository;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class ImovelService {
    @Autowired
    private ImovelRepository repository;

    @Cacheable(value = "imoveis")
    public List<Imovel> findAll(){return repository.findAll();}

    @Cacheable(value = "imoveis", key = "#id")
    public Imovel findById(UUID id) throws Exception{
        return repository.findById(id).orElseThrow(()-> new Exception("Imóvel não encontrado"));
    }
    @CacheEvict(value = "imoveis", allEntries = true)
    public Imovel save(Imovel imovel) throws Exception {
        if(imovel == null)
            throw new Exception("Id deve ser nulo");
        return repository.save(imovel);
    }

    @CacheEvict(value = "imoveis", allEntries = true)
    public Imovel update(Imovel imovel) throws Exception{
        if(imovel.getId() == null)
            throw new Exception("Id não pode ser nulo");

        var dbImovel = repository.findById(imovel.getId());
        if(dbImovel.isEmpty())
            throw new Exception("Id não pode ser nulo");

        var curImovel = dbImovel.get();
        curImovel.setEndereco(imovel.getEndereco());
        curImovel.setTipo_imovel(imovel.getTipoImovel());
        curImovel.setArea(imovel.getArea());
        curImovel.setPreco(imovel.getPreco());
        return repository.save(curImovel);
    }

    @CacheEvict(value = "imoveis", allEntries = true)
    public void delete(UUID id) throws Exception {
        try{
            repository.deleteById(id);
        } catch (Exception e){
            throw new Exception("Não é possivel apagar o imovel");
        }
    }
}