{ pkgs, lib, config, inputs, ... }:

{
  # https://devenv.sh/basics/
  env.GREET = "jakartaee-otel-vscode";

  # https://devenv.sh/packages/
  packages = [
    pkgs.git
    pkgs.opentelemetry-collector-contrib
    pkgs.vscode
    pkgs.podman
    pkgs.podman-compose
    ];

  # https://devenv.sh/languages/
  # languages.rust.enable = true;

  # https://devenv.sh/processes/
  # processes.cargo-watch.exec = "cargo-watch";
  processes.podman.exec =
    if pkgs.stdenv.isLinux then
      "echo Running on Linux, no podman machine required"
    else if pkgs.stdenv.isDarwin then
      "podman machine init; podman machine start"
    else
      "podman machine init; podman machine start";

  # https://devenv.sh/services/
  # services.postgres.enable = true;
  services.opentelemetry-collector.enable = true;
  services.opentelemetry-collector.configFile = ../otel-collector/minimal_config.yaml;


  # https://devenv.sh/scripts/
  scripts.hello.exec = ''
    echo hello from $GREET
  '';

  enterShell = ''
    hello
    git --version
    alias docker=podman
    code --install-extension ms-vscode-remote.remote-containers --force
    code --install-extension ms-vscode-remote.remote-containers --force --profile=luke
    code --install-extension ms-vscode-remote.remote-containers --force --profile=vader
    alias luke="code --profile luke $(pwd)/luke-service"
    alias vader="code --profile vader $(pwd)/vader-service"
  '';

  # https://devenv.sh/tasks/
  # tasks = {
  #   "myproj:setup".exec = "mytool build";
  #   "devenv:enterShell".after = [ "myproj:setup" ];
  # };

  # https://devenv.sh/tests/
  enterTest = ''
    echo "Running tests"
    git --version | grep --color=auto "${pkgs.git.version}"
  '';

  # https://devenv.sh/git-hooks/
  # git-hooks.hooks.shellcheck.enable = true;

  # See full reference at https://devenv.sh/reference/options/
}
