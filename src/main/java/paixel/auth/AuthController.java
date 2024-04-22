package paixel.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin
	@RestController
	@RequestMapping("/auth")
	@RequiredArgsConstructor
	public class AuthController {
	    
	    private final AuthService authService;
	    
	    @PostMapping(value = "login")
	    public ResponseEntity<AuthReponse> login(@RequestBody LoginRequest request)
	    {
	        return ResponseEntity.ok(authService.login(request));
	    }

	    @PostMapping(value = "register")
	    public ResponseEntity<AuthReponse> register(@RequestBody RegisterRequest request)
	    {
	        return ResponseEntity.ok(authService.register(request));
	    }
	}

