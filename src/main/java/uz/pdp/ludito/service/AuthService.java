package uz.pdp.ludito.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.ludito.repository.AgentRepository;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final AgentRepository agentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return agentRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
