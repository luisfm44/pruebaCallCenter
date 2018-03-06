package com.almundo.pruebaCallCenter.actores;

import com.almundo.pruebaCallCenter.main.Main;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Operador extends UntypedAbstractActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public enum Mensaje {
		ATENDER
	}
	
	

	public Operador() {
	}



	@Override
	public void onReceive(Object message) throws Throwable {
		if (message == Mensaje.ATENDER) {
			if (Thread.currentThread().isAlive()) {
				log.info("El operador esta ocupado ha recibido, envía "
						+ "mensaje al coordinador ");
				Main.coordinador.tell(Coordinador.Mensaje.ATENDER, getSelf());
			} else {
				log.info("El operador atiende la llamada, se bloquea el hilo ");
				getContext().stop(getSelf());
				Thread.sleep(Main.retornarAleatorio());
			}

		}
	}

}
