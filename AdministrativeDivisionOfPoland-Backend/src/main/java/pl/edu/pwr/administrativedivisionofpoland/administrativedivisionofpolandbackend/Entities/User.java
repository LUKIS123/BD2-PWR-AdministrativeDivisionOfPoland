package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Security.Roles.Role;
import pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Security.Token.Token;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "administratorzy")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id_admin", nullable = false, unique = true, updatable = false)
    int id;
    @Column(name = "login", nullable = false)
    String login;
    @Column(name = "haslo", nullable = false)
    String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.ADMIN.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
