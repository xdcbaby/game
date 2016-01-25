package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.WalkingQueue;
import net.scapeemulator.game.msg.ResetMinimapFlagMessage;
import net.scapeemulator.game.pf.AStarPathFinder;
import net.scapeemulator.game.pf.Path;
import net.scapeemulator.game.pf.PathFinder;
import net.scapeemulator.game.msg.WalkMessage;

public final class WalkMessageHandler extends MessageHandler<WalkMessage> {
    private PathFinder pathFinder = new AStarPathFinder();

    @Override
    public void handle(Player player, WalkMessage message) {
        int z = player.getPosition().getHeight();

        WalkingQueue queue = player.getWalkingQueue();

        Position destination = new Position(message.getDestination().getX(), message.getDestination().getY(), z);
        Position position = player.getPosition();

        int baseLocalX = position.getBaseLocalX();
        int baseLocalY = position.getBaseLocalY();
        int destLocalX = destination.getX() - baseLocalX;
        int destLocalY = destination.getY() - baseLocalY;

        Position base = new Position(baseLocalX, baseLocalY, position.getHeight());
        
        Path path = pathFinder.find(base, 104, position.getLocalX(), position.getLocalY(), destLocalX, destLocalY);
        if(path != null) {
            Position first = path.poll();
            if(first == null)
                return;

            queue.addFirstStep(first);
            queue.setRunningQueue(message.isRunning());
            player.stopAction();

            while(!path.getPoints().isEmpty()) {
                Position step = path.poll();
                queue.addStep(step);
            }
        } else {
            player.send(new ResetMinimapFlagMessage(message.getFlagId()));
            queue.reset();
        }
    }
}