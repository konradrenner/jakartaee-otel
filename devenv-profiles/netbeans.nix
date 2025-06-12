{ pkgs, lib, config, inputs, ... }:

{
  # https://devenv.sh/basics/
  env.GREET = "jakartaee-otel-netbeans";

  # https://devenv.sh/packages/
  packages = [
    pkgs.git
    pkgs.opentelemetry-collector-contrib
    pkgs.netbeans
    pkgs.gh
    ];

  # https://devenv.sh/languages/
  # languages.rust.enable = true;
  languages.java = {
    enable = true;
    jdk.package = pkgs.graalvmPackages.graalvm-ce;
    maven.enable = true;
  };

  # https://devenv.sh/processes/
  # processes.cargo-watch.exec = "cargo-watch";

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
    alias startide="netbeans --userdir $(pwd)/.netbeans"
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
