package com.example.Labs.service;

import com.example.Labs.model.DocumentEntity;
import com.example.Labs.repository.RepositoryEntity;

import com.example.Labs.service.dto.DtoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TestService {

    @Value("${test.value}")
    private String testedValue;

//public TestResponseDTO executeTestGet(){
//    log.info("get executed {} {}", testedValue, 1);
//    TestResponseDTO responseDTO = new TestResponseDTO();
//    responseDTO.setResponseText("Hello World GET");
//
//    return responseDTO;
//}
//
//public TestResponseDTO executeTestPost(TestRequestDTO testRequestDTO){
//    TestResponseDTO responseDTO = new TestResponseDTO();
//    responseDTO.setResponseText("Hello World POST"+ testRequestDTO.getTextData() + " " + testRequestDTO.getNumberData());
//    return responseDTO;
//}
//
//    public TestResponseDTO executeTestPut(TestRequestDTO testRequestDTO){
//        TestResponseDTO responseDTO = new TestResponseDTO();
//        responseDTO.setResponseText("Hello World PUT"+ testRequestDTO.getTextData() + " " + testRequestDTO.getNumberData());
//        return responseDTO;
//    }
//
//    public TestResponseDTO executeTestDelete(TestRequestDTO testRequestDTO){
//        TestResponseDTO responseDTO = new TestResponseDTO();
//        responseDTO.setResponseText("Hello World DELETE"+ testRequestDTO.getTextData() + " " + testRequestDTO.getNumberData());
//        return responseDTO;
//    }

    @Autowired
    public RepositoryEntity repositoryEntity;

public DtoEntity insertEntity(DtoEntity dtoEntity) {
    DocumentEntity documentEntity = new DocumentEntity();
    documentEntity.setName(dtoEntity.getName());
    documentEntity.setAccount_number(dtoEntity.getAccount_number());
    documentEntity.setPayment_story(dtoEntity.getPayment_story());
    documentEntity.setMoney(dtoEntity.getMoney());
    repositoryEntity.save(documentEntity);

    DtoEntity dtoEntityResponse = new DtoEntity();
    dtoEntityResponse.setId(documentEntity.getId());
    dtoEntityResponse.setName(documentEntity.getName());
    dtoEntityResponse.setAccount_number(documentEntity.getAccount_number());
    dtoEntityResponse.setPayment_story(documentEntity.getPayment_story());
    dtoEntityResponse.setMoney(documentEntity.getMoney());
    return dtoEntityResponse;
    }

    public DtoEntity updateEntity(int id, DtoEntity dtoEntity) {
        DtoEntity dtoEntityResponse = new DtoEntity();
        DocumentEntity documentEntity = repositoryEntity.findById(id).get(0);


        documentEntity.setName(dtoEntity.getName());
        if (dtoEntity.getName() == null)
            dtoEntity.setName(documentEntity.getName());

        documentEntity.setAccount_number(dtoEntity.getAccount_number());
        if (dtoEntity.getAccount_number() == null)
            dtoEntity.setAccount_number(documentEntity.getAccount_number());

        documentEntity.setPayment_story(dtoEntity.getPayment_story());
        if (dtoEntity.getPayment_story() == null)
            dtoEntity.setPayment_story(documentEntity.getPayment_story());

        documentEntity.setMoney(dtoEntity.getMoney());
        if (dtoEntity.getMoney() == null)
            dtoEntity.setMoney(documentEntity.getMoney());

        repositoryEntity.save(documentEntity);

        dtoEntityResponse.setId(documentEntity.getId());
        dtoEntityResponse.setName(documentEntity.getName());
        dtoEntityResponse.setAccount_number(documentEntity.getAccount_number());
        dtoEntityResponse.setPayment_story(documentEntity.getPayment_story());
        dtoEntityResponse.setMoney(documentEntity.getMoney());
        return dtoEntityResponse;
    }

    public String executeMoneyTransfer(Long senderId, Long receiverId, Long amount){
        DocumentEntity entitySender = repositoryEntity.findById(senderId).get();
        DtoEntity sender = new DtoEntity();
        sender.setId(entitySender.getId());
        sender.setName(entitySender.getName());
        sender.setAccount_number(entitySender.getAccount_number());
        sender.setPayment_story(entitySender.getPayment_story());
        sender.setMoney(entitySender.getMoney());

        DocumentEntity entityReceiver = repositoryEntity.findById(receiverId).get();
        DtoEntity receiver = new DtoEntity();
        receiver.setId(entityReceiver.getId());
        receiver.setName(entityReceiver.getName());
        receiver.setAccount_number(entityReceiver.getAccount_number());
        receiver.setPayment_story(entityReceiver.getPayment_story());
        receiver.setMoney(entityReceiver.getMoney());

        if (sender.getMoney() >= amount){
            sender.setMoney(sender.getMoney() - amount);
            receiver.setMoney(receiver.getMoney() + amount);
            sender.setPayment_story(sender.getPayment_story()+ " (Send:"+amount+" to "+receiver.getName()+")");
            receiver.setPayment_story(receiver.getPayment_story()+" (Get:"+amount+" from "+sender.getName()+")");
            updateEntity(sender.getId(), sender);
            updateEntity(receiver.getId(), receiver);
            return "Transfer operation successfully executed for users:" + sender.getName() + " -> " + receiver.getName() + " amount - " + amount;
        }
        return "Sender has not enough money";
    }

    public List<DtoEntity> findDocumentsById(Long id){
        List<DtoEntity> resultList = new ArrayList<>();
        repositoryEntity.findByIdGreaterThan(id).forEach(
                documentEntity -> {
                    DtoEntity dtoEntity = new DtoEntity();
                    dtoEntity.setName(documentEntity.getName());
                    dtoEntity.setAccount_number(documentEntity.getAccount_number());
                    dtoEntity.setPayment_story(documentEntity.getPayment_story());
                    dtoEntity.setMoney(documentEntity.getMoney());
                    dtoEntity.setId(documentEntity.getId());
                    resultList.add(dtoEntity);
                }
        );
        return resultList;
    }

public void deleteById(Long id){
    repositoryEntity.deleteById(id);
}

}
