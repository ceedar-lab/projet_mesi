package com.mesi;

import com.mesi.panels.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class GameTest {


//
//    @ParameterizedTest(name = "obstacle posX:{0} posY:{1} width:{2} height:{3} direction:{4} -> result {5}")
//    @CsvSource
//            ({
//                // test right direction
//                "10,0,10,10,1,false",
//                "0,10,10,10,1,false",
//                "10,20,10,10,1,false",
//                "20,10,10,10,1,true",
//                // test down direction
//                "10,0,10,10,2,false",
//                "0,10,10,10,2,false",
//                "10,20,10,10,2,true",
//                "20,10,10,10,2,false",
//                // test left direction
//                "10,0,10,10,3,false",
//                "0,10,10,10,3,true",
//                "10,20,10,10,3,false",
//                "20,10,10,10,3,false",
//                // test up direction
//                "10,0,10,10,4,true",
//                "0,10,10,10,4,false",
//                "10,20,10,10,4,false",
//                "20,10,10,10,4,false",
//
//            })
//    public void testCollisionChecker(Integer posX, Integer posY, Integer width, Integer height ,Integer direction ,Boolean result) throws IOException {
//        //Given
//        ArrayList<Rectangle> obstacleList = new ArrayList<>();
//        obstacleList.add(new Rectangle(posX,posY,width,height));
//        Rectangle character = new Rectangle(10,10,10,10);
//
//        switch (direction){
//            //right
//            case 1:{
//                character.setLocation(character.x+1,character.y);
//                break;
//            }
//            //down
//            case 2:{
//                character.setLocation(character.x,character.y+1);
//                break;
//            }
//            //left
//            case 3:{
//                character.setLocation(character.x-1,character.y);
//                break;
//            }
//            //up
//            case 4:{
//                character.setLocation(character.x,character.y-1);
//                break;
//            }
//        }
//        Game game = new Game();
//
//        //When
//        Boolean collision = game.collisionChecker(character,obstacleList);
//
//        //Then
//        Assertions.assertThat(result).isEqualTo(collision);
//
//    }
//
//
//    public void testTeleportChecker(){
//
//    }
//







}
