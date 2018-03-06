package com.almundo.pruebaCallCenter.main;

import java.util.Random;

import com.almundo.pruebaCallCenter.actores.Coordinador;
import com.almundo.pruebaCallCenter.actores.Operador;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.TypedActor.Supervisor;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static ActorRef operador;
	public static ActorRef coordinador;
	public static ActorRef supervisor;
	public static ActorSystem actorSystem;

	public static void main(String[] args) throws InterruptedException {
		actorSystem = ActorSystem.create("ActorSystem");
		operador = actorSystem.actorOf(Props.create(Operador.class), "operador");
		coordinador = actorSystem.actorOf(Props.create(Coordinador.class), "coordinador");
		supervisor = actorSystem.actorOf(Props.create(Supervisor.class), "minero");

		operador.tell(Operador.Mensaje.ATENDER, ActorRef.noSender());
		Thread.sleep(retornarAleatorio());
	}

	public static int retornarAleatorio() {
		int aleatorio = 0;
		try {
			Random rd = new Random();
			aleatorio = rd.nextInt(10) + 5;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aleatorio;
	}
}
