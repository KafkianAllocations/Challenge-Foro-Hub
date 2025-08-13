package com.alurachallenge.foro.hub.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * Este método retorna las autoridades (roles) del usuario.
     * En este caso, el usuario tiene el rol de "ROLE_USER".
     *
     * @return Colección de autoridades.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Retorna el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    @Override
    public String getUsername() {
        return username; // No es necesario reasignar el valor aquí
    }

    /**
     * Retorna la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password; // No es necesario reasignar el valor aquí
    }

    /**
     * Indica si la cuenta está expirada. En este caso siempre retorna true.
     *
     * @return true si la cuenta no está expirada.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta está bloqueada. En este caso siempre retorna true.
     *
     * @return true si la cuenta no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales están expiradas. En este caso siempre retorna
     * true.
     *
     * @return true si las credenciales no están expiradas.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta está habilitada. En este caso siempre retorna true.
     *
     * @return true si la cuenta está habilitada.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Si necesitas realizar la encriptación del password, puedes hacerlo en un
    // método adicional
    public void encryptPassword(String password) {
        // Ejemplo de cómo encriptar (utilizando BCrypt, por ejemplo)
        // this.password = new BCryptPasswordEncoder().encode(password);
    }
}
