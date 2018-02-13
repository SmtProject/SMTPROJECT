package com.smt.application.service;

import java.util.List;

import com.smt.data.entity.Admin;

public interface SmtImportService {

	public void importAdmin(List<Admin> admins) throws Exception;
}
