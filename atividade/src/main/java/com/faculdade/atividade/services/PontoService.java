package com.faculdade.atividade.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.faculdade.atividade.models.Ponto;
import com.faculdade.atividade.repository.PontoRepository;

import jakarta.transaction.Transactional;

@Service
public class PontoService {

    private PontoRepository pontoRepository;

    public PontoService(PontoRepository pontoRepository) {
        this.pontoRepository = pontoRepository;
    }

    @Transactional
    public List<Ponto> savePoints(){
        pontoRepository.makePoints();
        pontoRepository.makeZeroPoints();
        return pontoRepository.findAllWIthoutZeroPontos();
    }

    @Transactional
    public List<Ponto> getAllPoints(){
        return pontoRepository.findAllWIthoutZeroPontos();
    }

    @Transactional
    public List<Ponto> getAllPointsOfOneSeller(Long id){
        return pontoRepository.findAllOfOneSeller(id);
    }

    @Transactional
    public List<Ponto> convert(Long id, int value){
       List<Ponto> points = getAllPointsOfOneSeller(id);

        if (points.isEmpty()) 
            return Collections.emptyList();

        int i = 0;
        while(value > 0){
            if (i >= points.size()) 
                return null;
            
            int point_value = 0;
            Ponto point = points.get(i);
            if (point.getPoint() >= value && point.getPoint() > 0){
                point_value = point.getPoint();
                point.setPoint(point.getPoint() - value);
                value-= point_value;
                pontoRepository.save(point);
            }else{
                point_value = point.getPoint();
                point.setPoint(0);
                value-= point_value;
                pontoRepository.save(point);
            }
            i++;
        }
        return points;
    }    
}
