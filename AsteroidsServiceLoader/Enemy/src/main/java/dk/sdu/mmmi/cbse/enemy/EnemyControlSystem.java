/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


/**
 *
 * @author chris
 */
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            movingPart.setLeft((int) (Math.random() * 2) == 0);
            movingPart.setRight((int) (Math.random() * 2) == 0);
            movingPart.setUp((int) (Math.random() * 2) == 0);

//            if((int)(Math.random() * 40) == 0){
//                //System.out.println("Enemy is shooting!");
//                world.addEntity(shootProjectile(positionPart));
//            }
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

//    private Entity shootProjectile(PositionPart enemyPos) {
//        float deacceleration = 0;
//        float acceleration = 200;
//        float maxSpeed = 200;
//        float rotationSpeed = 0;
//        float x = enemyPos.getX();
//        float y = enemyPos.getY();
//        float radians = enemyPos.getRadians();
//        float radius = 2;
//
//        Entity projectile = new EnemyProjectile();
//        projectile.setRadius(radius);
//        projectile.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
//        projectile.add(new PositionPart(x, y, radians));
//
//        return projectile;
//    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
