package org.nikitin.config;

import lombok.Getter;
import org.nikitin.exception.AppException;
import picocli.CommandLine;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

@Getter
public class FileOutputOptions {

    @CommandLine.Option(names = "-a",
            description = "Append data to the end of existing files")
    private boolean appendMode;

    private Path dirOutputPath;
    private String prefix;

    @CommandLine.Option(names = "-o",
            description = "Output path for results (default: current directory)",
            paramLabel = "DIR",
            defaultValue = "")
    public void setDirOutputPath(String dirOutputPath) {
        if (dirOutputPath == null || dirOutputPath.trim().isEmpty()) {
            this.dirOutputPath = Path.of("");
            return;
        }
        String trimmedPath = dirOutputPath.trim();

        try {
            Path path = Path.of(trimmedPath);
            this.dirOutputPath = path.normalize();
        } catch (InvalidPathException e) {
            throw new AppException(Constants.INCORRECT_PATH_FOR_OUTPUT_FILES, e);
        }
    }

    @CommandLine.Option(names = "-p",
            description = "Prefix for output filenames (default: no prefix)",
            paramLabel = "PREFIX",
            defaultValue = "")
    public void setPrefix(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            this.prefix = "";
            return;
        }
        String trimmedPrefix = prefix.trim();
        validatePrefix(trimmedPrefix);
        this.prefix = trimmedPrefix;
    }

    private void validatePrefix(String prefix) {
        if (prefix.matches(Constants.REGEX_INVALID_FILENAME_CHARS)) {
            throw new AppException(Constants.PREFIX_CONTAINS_INVALID_CHARACTERS);
        }
    }
}
