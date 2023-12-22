package uz.iftixortalim.crmspring.repository.service.impl;

import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.model.Attendance;
import uz.iftixortalim.crmspring.repository.service.AttendanceRepositoryService;

import java.util.List;

@Service
public class AttendanceRepositoryServiceImpl implements AttendanceRepositoryService {
    @Override
    public List<Attendance> findByPagination() {
        return null;
    }
}
