package com.proyecto;

import com.proyecto.model.Rol;
import com.proyecto.model.Usuario;
import com.proyecto.repository.IRolRepository;
import com.proyecto.repository.IUsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProyectoClinicaMoreSaludApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoClinicaMoreSaludApplication.class, args);
	}
	@Bean
	CommandLineRunner init(PasswordEncoder encoder, IUsuarioRepository usuarioRepository, IRolRepository rolRepository) {
		return args -> {
			Rol admin = new Rol();
			admin.setNombre("ADMINISTRADOR");
			rolRepository.save(admin);

			Rol medico = new Rol();
			medico.setNombre("MEDICO");
			rolRepository.save(medico);

			Rol recepcionista = new Rol();
			recepcionista.setNombre("RECEPCIONISTA");
			rolRepository.save(recepcionista);

			Usuario u = new Usuario();
			u.setUsuario("admin");
			u.setPassword(encoder.encode("1234"));
			u.setRol(admin);
			usuarioRepository.save(u);

			Usuario u2 = new Usuario();
			u2.setUsuario("recepcionista");
			u2.setPassword(encoder.encode("1234"));
			u2.setRol(recepcionista);
			usuarioRepository.save(u2);

			Usuario u3 = new Usuario();
			u3.setUsuario("medico");
			u3.setPassword(encoder.encode("1234"));
			u3.setRol(medico);
			usuarioRepository.save(u3);
		};
	}
}
