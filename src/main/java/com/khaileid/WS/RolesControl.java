package com.khaileid.WS;

import com.khaileid.Entity.EntityRoles;
import com.khaileid.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RolesControl {
    @Autowired
    private RolesService rolesService;

    @GetMapping (value = "/getroles")
    public List<EntityRoles> getAllRolename(){
        return rolesService.getAllRolename();
    }

    @GetMapping (value = "/roles/{rolename}")
    public EntityRoles findByRolename(@Valid @PathVariable String rolename) {return rolesService.findByRolename(rolename);}


    @PostMapping (value = "/role")
    public ResponseEntity addRolename(@Valid @RequestBody EntityRoles entityRoles){return ResponseEntity.ok(rolesService.addRolename(entityRoles));}


    //Hard Delete
    //review.......................
    @PostMapping (value = "/roles/{rolename}")
    public void deleteByRolename(@PathVariable String rolename){
        rolesService.deleteByRolename(rolename);
    }


}
