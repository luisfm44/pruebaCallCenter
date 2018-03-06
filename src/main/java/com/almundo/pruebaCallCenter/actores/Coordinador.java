package com.almundo.pruebaCallCenter.actores;

import com.almundo.pruebaCallCenter.main.Main;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Coordinador extends UntypedAbstractActor{
	public enum Mensaje {
		ATENDER
	}

	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	@Override
	public void onReceive(Object message) throws Throwable {
		if (message == Mensaje.ATENDER) {
			if (Thread.currentThread().isAlive()) {
				log.info("El coordinador esta ocupado ha recibido, envía "
						+ "mensaje al supervisor ");
				Main.supervisor.tell(Coordinador.Mensaje.ATENDER, getSelf());
			} else {
				log.info("El coordinador atiende la llamada, se bloquea el hilo ");
				getContext().stop(getSelf());
				Thread.sleep(Main.retornarAleatorio());
			}

		}
	}

}
