package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPessoaDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedPersonToSave = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedPersonToSave);

        MessageResponseDTO expectedMessage = createMensagemEsperada(expectedPersonToSave.getId());
        MessageResponseDTO successMessage = personService.create(personDTO);

        assertEquals(expectedMessage, successMessage);
    }

    private MessageResponseDTO createMensagemEsperada(Long id) {
        return MessageResponseDTO.builder()
                                 .message("Person successfully created with ID "+ id)
                                 .build();
    }

}