package br.com.study.libraryapi.api.service;

import br.com.study.libraryapi.api.dto.LoanFilterDTO;
import br.com.study.libraryapi.api.model.entity.Book;
import br.com.study.libraryapi.api.model.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Loan save(Loan any);

    Optional<Loan> getById(Long id);


    Loan update(Loan loan);

    Page<Loan> find(LoanFilterDTO any, Pageable any1);

    Page<Loan> getLoansByBook(Book book, Pageable pageable);

    List<Loan> getAllLateLoan();
}
