package uz.iftixortalim.crmspring.dto.attendance;

import lombok.Data;
import uz.iftixortalim.crmspring.model.Attendance;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Data
public class AttendanceType {
    private final List<AttendanceBig> attendancesBig = new ArrayList<>();
    public List<AttendanceBig> add(List<Attendance> attendances) {
        List<Attendance> january = new ArrayList<>();
        List<Attendance> february = new ArrayList<>();
        List<Attendance> march = new ArrayList<>();
        List<Attendance> april = new ArrayList<>();
        List<Attendance> may = new ArrayList<>();
        List<Attendance> june = new ArrayList<>();
        List<Attendance> july = new ArrayList<>();
        List<Attendance> august = new ArrayList<>();
        List<Attendance> september = new ArrayList<>();
        List<Attendance> october = new ArrayList<>();
        List<Attendance> november = new ArrayList<>();
        List<Attendance> december = new ArrayList<>();

        for (Attendance attendance : attendances) {
            Month month = attendance.getAttendanceDate().getMonth();
            switch (month.getValue()) {
                case 1:
                    january.add(attendance);
                    break;
                case 2:
                    february.add(attendance);
                    break;
                case 3:
                    march.add(attendance);
                    break;
                case 4:
                    april.add(attendance);
                    break;
                case 5:
                    may.add(attendance);
                    break;
                case 6:
                    june.add(attendance);
                    break;
                case 7:
                    july.add(attendance);
                    break;
                case 8:
                    august.add(attendance);
                    break;
                case 9:
                    september.add(attendance);
                    break;
                case 10:
                    october.add(attendance);
                    break;
                case 11:
                    november.add(attendance);
                    break;
                case 12:
                    december.add(attendance);
                    break;
            }
        }
        List<AttendanceSmall> smallList = new ArrayList<>();

        if (!january.isEmpty()) {
            for (Attendance attendance : january) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Yanvar", january.get(0).getGroup().getDirection(), smallList));
        }
        if (!february.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : february) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Febral", february.get(0).getGroup().getDirection(), smallList));
        }
        if (!march.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : march) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Mart", march.get(0).getGroup().getDirection(), smallList));
        }
        if (!april.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : april) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Aprel", april.get(0).getGroup().getDirection(), smallList));
        }
        if (!may.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : may) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("May", may.get(0).getGroup().getDirection(), smallList));
        }
        if (!june.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : june) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Iyun", june.get(0).getGroup().getDirection(), smallList));
        }
        if (!july.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : july) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Iyul", july.get(0).getGroup().getDirection(), smallList));
        }
        if (!august.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : august) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Avgust", august.get(0).getGroup().getDirection(), smallList));
        }
        if (!september.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : september) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Sentyabr", september.get(0).getGroup().getDirection(), smallList));
        }
        if (!october.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : october) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Oktyabr", october.get(0).getGroup().getDirection(), smallList));
        }
        if (!november.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : november) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Noyabr", november.get(0).getGroup().getDirection(), smallList));

        }
        if (!december.isEmpty()) {
            smallList = new ArrayList<>();
            for (Attendance attendance : december) {
                smallList.add(new AttendanceSmall(attendance.getAttendanceDate(), attendance.getAttendance()));
            }
            attendancesBig.add(new AttendanceBig("Dekabr", december.get(0).getGroup().getDirection(), smallList));
        }

        return attendancesBig;
    }
}
