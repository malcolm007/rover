package com.rover;

import com.rover.models.RoverMission;
import com.rover.models.RoverMissionImpl;
import com.rover.parser.FileParser;
import com.rover.parser.Parser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {

        Parser parser = new FileParser();
        RoverMission roverMission = new RoverMissionImpl()
                .build(parser.parse(args[0]));
        //On récupère chaque rover et on fait move() ou execute()
        //reoverMission.execute() renvoi un resultat qui contient l'historique de move
        // On affiche la dernière position
        // Pour info, voici tous le trajet du rover
        roverMission.move();
    }
}
