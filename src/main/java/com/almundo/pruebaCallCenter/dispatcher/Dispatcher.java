package com.almundo.pruebaCallCenter.dispatcher;

import com.almundo.pruebaCallCenter.actores.Operador;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinRouter;

import com.typesafe.config.ConfigFactory;

public class Dispatcher {

	public Dispatcher() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ActorSystem _system = ActorSystem.create("default-dispatcher",
				ConfigFactory.load().getConfig("my-dispatcher"));
		
		ActorRef actor = _system.actorOf(new Props(Operador.class).withDispatcher("defaultDispatcher").withRouter(
				new RoundRobinRouter(5)));
		
		for (int i = 0; i < 10; i++) {
			actor.tell(i, actor);
		}
		_system.stop(actor);
	}

}
