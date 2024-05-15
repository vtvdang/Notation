public class QueueUnderflowException extends Exception {
	QueueUnderflowException() {
		super("Queue is empty");
	}
}