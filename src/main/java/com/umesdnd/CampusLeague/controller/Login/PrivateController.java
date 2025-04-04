package com.umesdnd.CampusLeague.controller.Login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PrivateController {

    @GetMapping("/")
    public String denegado() {
        return "validacion correcta";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        return "Authentication and Authorization";
    }

}
