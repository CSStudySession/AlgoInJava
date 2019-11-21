package Dropbox.哦你赛;

import common.SegmentTree;

import java.util.ArrayList;
import java.util.List;

public class BitTorrent {

    public boolean isDownloadComplete(int[][] chunks, int size) {
        if (chunks == null || chunks.length == 0) {
            return false;
        }

        // if closing number is exclusive, then this works
        for (int i = 0; i < chunks.length; ++i) {
            size -= (chunks[i][1] - chunks[i][0]);
        }

        return size == 0;
    }

    List<List<Integer>> chunks = new ArrayList<>();

    private SegmentTree segmentTree = new SegmentTree();
    private int size = 0;
    public void init(int fileSize) {
        segmentTree.build(new int[fileSize], fileSize);
        size = fileSize;
    }

    public void addChunk(List<Integer> chunk) {
        for (int i = 0; i < chunk.size(); ++i) {
            segmentTree.updateTreeNode(chunk.get(i), 1);
        }
    }

    public boolean isDownloadCompleteOftenCalled() {
        return segmentTree.getTree()[1] == size;
    }
}
