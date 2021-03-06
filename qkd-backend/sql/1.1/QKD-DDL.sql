CREATE  TABLE `QKD`.`Simulation_Suite` (
  `Simulation_Suite_id` INT NOT NULL AUTO_INCREMENT ,
  `Sim_Num` INT NOT NULL ,
  `Start_T` DATETIME NOT NULL ,
  `End_T` DATETIME NOT NULL ,
  PRIMARY KEY (`Simulation_Suite_id`) );

  ALTER TABLE `QKD`.`Simulation_Suite` 
ADD UNIQUE INDEX `Simulation_Suite_id_UNIQUE` (`Simulation_Suite_id` ASC) ;

 CREATE  TABLE `QKD`.`Simulation` (
  `Sim_Id` INT NOT NULL AUTO_INCREMENT ,
  `Eve` CHAR NOT NULL DEFAULT 'N' ,
  `Eve_Detected` CHAR NULL ,
  `Eve_Known_Filters` INT NULL ,
  `Filter_Size` INT NOT NULL ,
  `Filter_Set_Match` INT NOT NULL ,
  `Eve_Impacted_Bits` INT NULL ,
  `Bits_Correct` INT NOT NULL ,
  `Suite_ID` INT(11) NULL ,
  `Run_T` DATETIME NULL ,
  PRIMARY KEY (`Sim_Id`) ,
  UNIQUE INDEX `Sim_Id_UNIQUE` (`Sim_Id` ASC) ,
  INDEX `FK_SUITE_ID_idx` (`Suite_ID` ASC) ,
  CONSTRAINT `FK_SUITE_ID`
    FOREIGN KEY (`Suite_ID` )
    REFERENCES `QKD`.`Simulation_Suite` (`Simulation_Suite_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
