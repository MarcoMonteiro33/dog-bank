package br.com.ironcoorp.dogbank.domain.builder;


import br.com.ironcoorp.dogbank.repository.TutorRepository;
import br.com.ironcoorp.dogbank.exception.CPFCadastradoException;
import br.com.ironcoorp.dogbank.exception.IdadeNaoPermitidaException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class TutorBuilderTest {

    private TutorRepository tutorRepository;
    private TutorBuilder tutorBuilder;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setuo() throws CPFCadastradoException {

        tutorRepository = Mockito.mock(TutorRepository.class);
        tutorBuilder = new TutorBuilder();
    }

    @Test(expected = IdadeNaoPermitidaException.class)
    public void deveLancarExceptionIdadeMenorQue18Anos() throws IdadeNaoPermitidaException, CPFCadastradoException {

        tutorBuilder.nome("Marco")
                .sobrenome("Monteiro")
               // .DataNascimento(LocalDate.now())
                .cnh("11111111111l")
                .cpf("11111111111")
                .build();
    }
}
