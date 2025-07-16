package com.notevault.controllers;

import com.notevault.models.AuditLog;
import com.notevault.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        auditLogService.getAllAuditLogs();
        return null;
    }

    @GetMapping("/note/{id}")
    public List<AuditLog> getAuditLogsByNoteId(@PathVariable Long id) {
        return auditLogService.getAuditLogsForNoteId(id);
    }


}
