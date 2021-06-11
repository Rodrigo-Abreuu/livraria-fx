package threads;

public class TestandoThreads {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Rodando em paralelo");
			}
		}).start();
		System.out.println("Fim");
	}

}
