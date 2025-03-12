@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Existing endpoints...

    @PutMapping("/updateProfile")
    public ResponseEntity<ApiResponseDto<?>> updateProfile(@RequestBody UpdateProfileRequestDto updateProfileRequestDto) {
        return authService.updateProfile(updateProfileRequestDto);
    }
}