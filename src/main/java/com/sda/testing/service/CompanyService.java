package com.sda.testing.service;

import com.sda.testing.exception.InvalidOperation;
import com.sda.testing.model.EmployeeDto;
import com.sda.testing.model.EmployeeLevel;
import com.sda.testing.model.TeamDto;
import com.sda.testing.repository.EmployeeRepository;
import com.sda.testing.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    /**
     * Return sum of all salaries.
     * @return sum of salaries.
     */
    public double summarizeSalaries(){
        return 0;
    }

    /**
     * Return sum of salaries on a given level.
     * @param level - employee level of which salaries should be summarized. Can't be null.
     * @return sum of salaries.
     */
    public double salaries(EmployeeLevel level){
        return 0.0;
    }

    /**
     * Adds new Employee to the company.
     * @param employeeDto - dto containing all employee information.
     * @throws InvalidOperation can be thrown if name, surname, or salary has not been provided.
     */
    public void hireEmployee(EmployeeDto employeeDto) throws InvalidOperation {

    }

    /**
     * Fire employee with given id.
     * @param employeeId - employee which should be fired.
     */
    public void fireEmployee(Long employeeId){

    }

    /**
     * Create an empty team.
     * @param teamName - name of the team. Name has to be unique.
     * @throws InvalidOperation - exception might be thrown if team name is not unique.
     */
    public void createTeam(String teamName) throws InvalidOperation{

    }

    /**
     * Remove team with given name.
     * @param teamName name of the team to remove.
     * @throws InvalidOperation - if team name is incorrect/or null or team does not exist, exception will be thrown.
     */
    public void removeTeam(String teamName) throws InvalidOperation{

    }

    /**
     * List team names.
     */
    public List<String> listTeams(){
        return null;
    }

    /**
     * Add employee to team. Team might not have more than 6 members. Single Employee can't be in two teams.
     * In team there can be max one Lead and max one Manager.
     *
     * @param employeeId - employee identifier.
     * @param teamName - team name
     * @throws InvalidOperation - exception might be thrown if this operation is invalid.
     */
    public void addEmployeeToTeam(Long employeeId, String teamName) throws InvalidOperation{

    }

    /**
     * Remove employees team assignment.
     * @param employeeId - employee of which team has to be removed.
     */
    public void removeEmployeeFromTeam(Long employeeId){

    }

    /**
     * Find team with given name and return it's info.
     * @param teamName - name of an existing team.
     * @return transfer object with team info.
     * @throws InvalidOperation can be thrown if team does not exist, it's name is invalid or null.
     */
    public TeamDto teamInfo(String teamName) throws InvalidOperation{
        return null;
    }
}
