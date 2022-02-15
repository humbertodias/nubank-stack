package nubank.service.account.service;

import nubank.service.account.domain.CustomerDocument;
import nubank.service.account.dto.CustomerDocumentDTO;
import nubank.service.account.exception.InvalidRequest;
import nubank.service.account.repository.CustomerDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDocumentService {

    @Autowired
    CustomerDocumentRepository customerDocumentRepository;

    public CustomerDocument create(CustomerDocumentDTO dto) throws InvalidRequest {
        var found = customerDocumentRepository.findById(dto.getId());

        // validaçao
        if(found != null) throw new InvalidRequest("Nome " + dto.getId() + " já cadastrado");

        return customerDocumentRepository.insert(dto.toEntity());
    }

    public List<CustomerDocument> getAll(){
        return customerDocumentRepository.findAll();
    }
    public Optional<CustomerDocument> get(String id){
        return customerDocumentRepository.findById(id);
    }

    public void delete(String id){
        customerDocumentRepository.deleteById(id);
    }

    public CustomerDocument update(CustomerDocumentDTO dto){
        var entity = dto.toEntity();
        return customerDocumentRepository.save(entity);
    }

}
