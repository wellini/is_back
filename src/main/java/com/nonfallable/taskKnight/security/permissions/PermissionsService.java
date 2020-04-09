package com.nonfallable.taskKnight.security.permissions;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.nonfallable.taskKnight.models.Role;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionsService {

    private final String PERMISSION_DESCRIPTIONS_FILE_NAME = "permissions.csv";

    private Map<PermissionMapping, Permission> permissionsIndex = new HashMap<>();

    @PostConstruct
    private void loadPermissionDescriptions() throws IOException {
        Resource resource = new ClassPathResource(PERMISSION_DESCRIPTIONS_FILE_NAME);
        File file = resource.getFile();
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<Permission> readValues =
                mapper.readerFor(Permission.class).with(bootstrapSchema).readValues(file);
        readValues.readAll()
                .forEach(permission -> permissionsIndex.put(PermissionMapping.valueOf(permission.getId()), permission));
    }

    public Permission getPermission(PermissionMapping mapping) {
        return permissionsIndex.get(mapping);
    }

    public List<Permission> getPermissionsByRole(Role role) {
        return PermissionMapping.getMappingsByRole(role).stream()
                .map(this::getPermission)
                .collect(Collectors.toList());
    }
}
