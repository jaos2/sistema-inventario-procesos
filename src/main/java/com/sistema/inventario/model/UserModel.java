package com.sistema.inventario.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Primer nombre es requerido")
    @Size(min= 1, max = 100, message = "El primer nombre debe tener de 1 a 100 caracteres")
    private String firstName;
    
    @NotBlank(message = "El apellido es requerido")
    @Size(min= 1, max = 100, message = "El apellido debe tener de 1 a 100 caracteres")
    private String lastName;
    
    
    @NotBlank(message = "Email es requerido")
    @Email(message = "El email debe ser valido")
    @Column(unique = true, nullable = false)
    private String email;
    
    @NotBlank(message = "El telefono es requerido")
    @Size(min= 1, max = 16, message = "El numero de telefono debe tener de 1 a 16 caracteres")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "El telegono debe tener 10 caracteres")
    private String phone;
    
    @NotNull(message = "Contraseña requerida")
    @Size(min = 8, max = 255,message = "la contraseña debe tener de 8 a 255 caracteres")
    private String password;
    
    @NotBlank(message = "Documento requerido")
    @Column(unique = true, nullable = false)
    @Size(min= 5, max = 20, message = "El numero de documento debe tener de 5 a 20 caracteres")
    private String document;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
