package edu.du.sb1021.service;

import edu.du.sb1021.entity.Role;
import edu.du.sb1021.repository.MemberRepository;
import edu.du.sb1021.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    final RoleRepository rr;
    final MemberRepository mr;

    public List<Role> findAllWithRole(){
        return rr.findAll();
    }
    public Optional<Role> findById(Long id){
        return rr.findById(id);
    }
    public void save(Role role){
        rr.save(role);
    }
}
