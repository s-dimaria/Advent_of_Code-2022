import java.util.ArrayList;

public class Constants {

    public static final ArrayList<char[][]> ROCKS_TYPES = new ArrayList<>();
    public static final ArrayList<Character> ROCKS_NAMES = new ArrayList<>();

    public static final int WIDE = 7;

    public static void initializedTypes() {

        ROCKS_NAMES.add('-');
        ROCKS_NAMES.add('+');
        ROCKS_NAMES.add('L');
        ROCKS_NAMES.add('I');
        ROCKS_NAMES.add('o');


        ROCKS_TYPES.add(new char[][]{   {'.','.','@','@','@','@','.'}   });

        ROCKS_TYPES
                .add(new char[][]{  {'.','.','.','@','.','.','.'},
                                    {'.','.','@','@','@','.','.'},
                                    {'.','.','.','@','.','.','.'}   });

        ROCKS_TYPES
                .add(new char[][]{  {'.','.','.','.','@','.','.'},
                                    {'.','.','.','.','@','.','.'},
                                    {'.','.','@','@','@','.','.'}   });

        ROCKS_TYPES.add(new char[][]{   {'.','.','@','.','.','.','.'},
                                        {'.','.','@','.','.','.','.'},
                                        {'.','.','@','.','.','.','.'},
                                        {'.','.','@','.','.','.','.'}   });

        ROCKS_TYPES.add(new char[][]{   {'.','.','@','@','.','.','.'},
                                        {'.','.','@','@','.','.','.'}   });
    }
}
