package uz.iftixortalim.crmspring.repository.service;

import uz.iftixortalim.crmspring.model.Attendance;

import java.util.List;

public interface AttendanceRepositoryService {
    List<Attendance> findByPagination();
}
