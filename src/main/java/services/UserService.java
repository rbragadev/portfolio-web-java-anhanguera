package services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.User;
import repositories.UserRepository;
import services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User usuario){
        return repository.save(usuario);
    }

    public void delete(long id){
        try {
        repository.deleteById(id);
        } catch (ResourceNotFoundException e){

        }
    }

    public User update(long id, User usuario){
        try {
        User cadastro = repository.getReferenceById(id);
        updateDados(cadastro, usuario);
        return repository.save(cadastro);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    private void updateDados(User cadastro, User usuario) {
        cadastro.setNome(usuario.getNome());
        cadastro.setEmail(usuario.getEmail());
        cadastro.setTelefone(usuario.getTelefone());
    }


}
