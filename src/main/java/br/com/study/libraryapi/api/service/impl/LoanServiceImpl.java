package br.com.study.libraryapi.api.service.impl;

import br.com.study.libraryapi.api.dto.LoanFilterDTO;
import br.com.study.libraryapi.api.model.entity.Book;
import br.com.study.libraryapi.api.model.entity.Loan;
import br.com.study.libraryapi.api.model.repository.LoanRepository;
import br.com.study.libraryapi.api.service.LoanService;
import br.com.study.libraryapi.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {

        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        if(repository.findExistsByBookAndNotReturned(loan.getBook())){
            throw  new BusinessException("Book already loaned");
        }
        return repository.save(loan);
    }

    @Override
    public Optional<Loan> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Loan update(Loan loan) {
        return repository.save(loan);
    }

    @Override
    public Page<Loan> find(LoanFilterDTO filterDTO, Pageable pageable) {
        return repository.findByBookIsbnOrCustomer(filterDTO.getIsbn(),filterDTO.getCustomer(),pageable);
    }

    @Override
    public Page<Loan> getLoansByBook(Book book, Pageable pageable) {
        return repository.findByBook(book,pageable);
    }

    @Override
    public List<Loan> getAllLateLoan() {
        final Integer loanDays = 4;
        LocalDate threeDaysAgo = LocalDate.now().minusDays(loanDays);

        return repository.findByLoanDateLessThanAndNotReturned(threeDaysAgo);
    }
}
