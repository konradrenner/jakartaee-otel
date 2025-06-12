# devenv.nix
{ pkgs, lib, config, inputs, ... }:

let
  # Environment variable to select profile
  profile = builtins.getEnv "DEVENV_PROFILE";
  activeProfile = if profile == "" then "netbeans" else profile;

  profiles = {
    vscode = import ./devenv-profiles/vscode.nix { inherit pkgs lib config inputs; };
    netbeans = import ./devenv-profiles/netbeans.nix { inherit pkgs lib config inputs; };
  };
in
  profiles.${activeProfile}
