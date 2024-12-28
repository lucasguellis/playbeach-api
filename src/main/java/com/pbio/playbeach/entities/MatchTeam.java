package com.pbio.playbeach.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "match_teams")
public class MatchTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_teams_id_gen")
    @SequenceGenerator(name = "match_teams_id_gen", sequenceName = "match_teams_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "team1")
    private Set<com.pbio.playbeach.entities.Match> team1 = new LinkedHashSet<>();

    @OneToMany(mappedBy = "team2")
    private Set<com.pbio.playbeach.entities.Match> team2 = new LinkedHashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<com.pbio.playbeach.entities.UserTeam> userTeams = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<com.pbio.playbeach.entities.Match> getTeam1() {
        return team1;
    }

    public void setTeam1(Set<com.pbio.playbeach.entities.Match> team1) {
        this.team1 = team1;
    }

    public Set<com.pbio.playbeach.entities.Match> getTeam2() {
        return team2;
    }

    public void setTeam2(Set<com.pbio.playbeach.entities.Match> team2) {
        this.team2 = team2;
    }

    public Set<com.pbio.playbeach.entities.UserTeam> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(Set<com.pbio.playbeach.entities.UserTeam> userTeams) {
        this.userTeams = userTeams;
    }

}