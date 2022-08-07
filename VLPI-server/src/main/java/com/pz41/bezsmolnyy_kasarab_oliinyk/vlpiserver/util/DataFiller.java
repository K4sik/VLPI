package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.*;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.Module;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.Difficulty;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.UserRole;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataFiller {

    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    final ModuleRepository moduleRepository;
    final TaskTypeRepository taskTypeRepository;
    final TaskRepository taskRepository;
    final TaskDetailsRepository taskDetailsRepository;
    final RequirementTypeRepository requirementTypeRepository;
    final RequirementRepository requirementRepository;
    final TaskDetailsRequirementRepository taskDetailsRequirementRepository;

    public DataFiller(PasswordEncoder passwordEncoder, UserRepository userRepository, ModuleRepository moduleRepository,
                      TaskTypeRepository taskTypeRepository, TaskRepository taskRepository,
                      TaskDetailsRepository taskDetailsRepository, RequirementTypeRepository requirementTypeRepository,
                      RequirementRepository requirementRepository, TaskDetailsRequirementRepository taskDetailsRequirementRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
        this.taskTypeRepository = taskTypeRepository;
        this.taskRepository = taskRepository;
        this.taskDetailsRepository = taskDetailsRepository;
        this.requirementTypeRepository = requirementTypeRepository;
        this.requirementRepository = requirementRepository;
        this.taskDetailsRequirementRepository = taskDetailsRequirementRepository;
    }

    private void fillUserData() {
        userRepository.save(new User("Admin", "admin@admin.com", passwordEncoder.encode("admin"), UserRole.ROLE_ADMIN));
        userRepository.save(new User("Dudoxa Dudos", "dudoxa@ddos.com", passwordEncoder.encode("dudoxa"), UserRole.ROLE_STUDENT));
    }

    private void fillModuleData() {
        moduleRepository.save(new Module("Requirement Analysis"));
        moduleRepository.save(new Module("Design"));
        moduleRepository.save(new Module("Modeling"));
        moduleRepository.save(new Module("Coding"));
        moduleRepository.save(new Module("Testing"));
    }

    private void fillTaskTypeData() {
        Module module = moduleRepository.getById(1L);
        taskTypeRepository.save(new TaskType("Quiz", module));
        taskTypeRepository.save(new TaskType("Drag&Drop", module));
    }

    private void fillTaskData() {
        TaskType quiz = taskTypeRepository.getById(1L);
        TaskType dragAndDrop = taskTypeRepository.getById(2L);

        User admin = userRepository.getById(1L);

        taskRepository.save(new Task(Difficulty.EASY, quiz, "Creative title", "Creative description", admin, (short) 300));
        taskRepository.save(new Task(Difficulty.MEDIUM, dragAndDrop, "Very creative title", "Very creative description", admin, (short) 300));
        taskRepository.save(new Task(Difficulty.MEDIUM, quiz, "Monstrously creative title", "Monstrously creative description", admin, (short) 420));
        taskRepository.save(new Task(Difficulty.HARD, quiz, "Exceedingly creative title", "Exceedingly creative description", admin, (short) 300));
        taskRepository.save(new Task(Difficulty.EASY, dragAndDrop, "Divinely creative title", "Divinely creative description", admin, (short) 420));
        taskRepository.save(new Task(Difficulty.EASY,  quiz, "Unbelievably creative title", "Unbelievably creative description", admin, (short) 300));
        taskRepository.save(new Task(Difficulty.MEDIUM, dragAndDrop, "Outwordly creative title", "Outwordly creative description", admin, (short) 420));
    }

    private void fillTaskDetailsData() {
        Task quiz = taskRepository.getById(1L);
        Task dragAndDrop = taskRepository.getById(2L);
        Task dd = taskRepository.getById(5L);

        taskDetailsRepository.save(new TaskDetails("Which requirement violates atomicity?", quiz));
        taskDetailsRepository.save(new TaskDetails("Which requirement violates clearness?", quiz));
        taskDetailsRepository.save(new TaskDetails("Which requirement violates correctness?", quiz));

        taskDetailsRepository.save(new TaskDetails("Drag UI requirements to solution area", dragAndDrop));
        taskDetailsRepository.save(new TaskDetails("What are the software requirements?", dd));

    }

    private void fillTaskDetailsRequirementData() {
        TaskDetails atomicity = taskDetailsRepository.getById(1L);

        Requirement atomicityCorrect = requirementRepository.getById(3L);
        Requirement atomicityWrong1 = requirementRepository.getById(5L);
        Requirement atomicityWrong2 = requirementRepository.getById(6L);
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(atomicity, atomicityCorrect,true, 1.05f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(atomicity, atomicityWrong1,false, 0));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(atomicity, atomicityWrong2,false, 0));

        TaskDetails clearness = taskDetailsRepository.getById(2L);

        Requirement clearnessCorrect = requirementRepository.getById(4L);
        Requirement clearnessWrong1 = requirementRepository.getById(7L);
        Requirement clearnessWrong2 = requirementRepository.getById(8L);
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(clearness, clearnessCorrect, true, 1.3f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(clearness, clearnessWrong1, false, 0));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(clearness, clearnessWrong2, false, 0));

        TaskDetails correctness = taskDetailsRepository.getById(3L);

        Requirement correctnessCorrect = requirementRepository.getById(9L);
        Requirement correctnessWrong1 = requirementRepository.getById(1L);
        Requirement correctnessWrong2 = requirementRepository.getById(10L);
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(correctness, correctnessCorrect, true, 1.2f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(correctness, correctnessWrong1, false, 0));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(correctness, correctnessWrong2, false, 0));

        TaskDetails dragAndDrop = taskDetailsRepository.getById(4L);

        Requirement ddCorrect1 = correctnessWrong1;
        Requirement ddCorrect2 = requirementRepository.getById(2L);

        Requirement ddWrong1 = requirementRepository.getById(6L);
        Requirement ddWrong2 = clearnessWrong1;
        Requirement ddWrong3 = clearnessWrong2;

        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dragAndDrop, ddCorrect1, true, 0.95f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dragAndDrop, ddCorrect2, true, 1.2f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dragAndDrop, ddWrong1, false, 0));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dragAndDrop, ddWrong2, false, 0));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dragAndDrop, ddWrong3, false, 0));

        TaskDetails dd = taskDetailsRepository.getById(5L);

        Requirement ddC1 = requirementRepository.getById(5L);
        Requirement ddC2 = requirementRepository.getById(6L);
        Requirement ddC3 = requirementRepository.getById(10L);

        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dd, ddC1, true, 0.95f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dd, ddC2, true, 1.05f));
        taskDetailsRequirementRepository.save(new TaskDetailsRequirement(dd, ddC3, true, 1.10f));
    }

    private void fillRequirementTypeData() {
        requirementTypeRepository.save(new RequirementType("UI Requirement"));
        requirementTypeRepository.save(new RequirementType("Functional Requirement"));
        requirementTypeRepository.save(new RequirementType("Security Requirement"));
        requirementTypeRepository.save(new RequirementType("Reliability Requirement"));
        requirementTypeRepository.save(new RequirementType("Availability Requirement"));
        requirementTypeRepository.save(new RequirementType("Internationalization and Localization Requirement"));
        requirementTypeRepository.save(new RequirementType("Accessibility Requirement"));
        requirementTypeRepository.save(new RequirementType("Other"));
    }

    private void fillRequirementData() {
        RequirementType ui = requirementTypeRepository.getById(1L);
        RequirementType fun = requirementTypeRepository.getById(2L);
        RequirementType sec = requirementTypeRepository.getById(3L);
        RequirementType ava = requirementTypeRepository.getById(5L);
        RequirementType il = requirementTypeRepository.getById(6L);
        RequirementType ot = requirementTypeRepository.getById(8L);

        requirementRepository.save(new Requirement("Button must be green", ui));
        requirementRepository.save(new Requirement("Header must have a size of 14em", ui));
        requirementRepository.save(new Requirement("Task wording must be red, bold, aligned to the right side", ui));
        requirementRepository.save(new Requirement("UI must be pleasant to look at", ui));
        requirementRepository.save(new Requirement("Admin must be able to edit task details", fun));
        requirementRepository.save(new Requirement("System must provide tools for users to perform tasks", fun));
        requirementRepository.save(new Requirement("User password must be stored in encrypted state with the use of SHA-256 algorithm", sec));
        requirementRepository.save(new Requirement("Application must support English and Ukrainian language", il));
        requirementRepository.save(new Requirement("User must be sober when interacting with the system", ot));
        requirementRepository.save(new Requirement("System must be available 24/7/365", ava));
    }

    public void fillData() {
        fillUserData();
        fillModuleData();
        fillTaskTypeData();
        fillTaskData();
        fillTaskDetailsData();
        fillRequirementTypeData();
        fillRequirementData();
        fillTaskDetailsRequirementData();
    }
}
