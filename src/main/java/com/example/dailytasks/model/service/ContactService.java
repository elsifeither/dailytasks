package com.example.dailytasks.model.service;

import com.example.dailytasks.model.dto.ContactDTO;
import com.example.dailytasks.model.entity.Contact;
import com.example.dailytasks.model.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    public ContactService(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    public void saveContact(ContactDTO contactDTO) {
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        contactRepository.save(contact);
    }
}
