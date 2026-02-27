package umg.edu.gt.handler;

import umg.edu.gt.data_structure.queue.manual.QueueLinked;

public class Main {

    private static QueueLinked<Song> highPriority = new QueueLinked<>();
    private static QueueLinked<Song> normalPriority = new QueueLinked<>();
    private static int totalSongsPlayed = 0;
    private static int totalTimePlayed = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("[LOG] Starting playlist...");

        addSong(new Song("Song A", "Artist A", 8, 1));
        addSong(new Song("Song B", "Artist B", 12, 2));
        addSong(new Song("Song C", "Artist C", 6, 1));
        addSong(new Song("Song D", "Artist D", 10, 2));

        while (!highPriority.isEmpty() || !normalPriority.isEmpty()) {
            Song current = !highPriority.isEmpty() ? highPriority.dequeue() : normalPriority.dequeue();
            playSong(current);
        }

        System.out.println("[LOG] Playlist finished.");
        System.out.println("[LOG] Total songs played: " + totalSongsPlayed);
        System.out.println("[LOG] Total time played: " + totalTimePlayed + " seconds");
    }

    private static void addSong(Song song) {
        if (song.getPriority() == 1) {
            highPriority.enqueue(song);
        } else {
            normalPriority.enqueue(song);
        }
    }

    private static void playSong(Song song) throws InterruptedException {
        System.out.println("[LOG] Now playing: " + song.getTitle() + " - " + song.getArtist() + " (" + song.getDuration() + "s)");
        
        for (int i = 1; i <= song.getDuration(); i++) {
            Thread.sleep(1000);
            System.out.println("[LOG] Playing: " + song.getTitle() + " | " + i + "s / " + song.getDuration() + "s " + progressBar(i, song.getDuration()));
        }

        totalSongsPlayed++;
        totalTimePlayed += song.getDuration();
        System.out.println("[LOG] Finished: " + song.getTitle());
    }

    private static String progressBar(int current, int total) {
        int totalBars = 10;
        int filledBars = (current * totalBars) / total;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalBars; i++) {
            bar.append(i < filledBars ? "#" : "-");
        }
        bar.append("]");
        return bar.toString();
    }
}
