/*
@license

dhtmlxGantt v.4.1.0 Professional Evaluation
This software is covered by DHTMLX Evaluation License. Contact sales@dhtmlx.com to get Commercial or Enterprise license. Usage without proper license is prohibited.

(c) Dinamenta, UAB.
*/
gantt.plugin(function (t) {
  t._groups = {
    relation_property: null, relation_id_property: "$group_id", group_id: null, group_text: null, loading: !1, loaded: 0, init: function (t) {
      var e = this; t.attachEvent("onClear", function () { e.clear() }), e.clear(); var i = t._get_parent_id; t._get_parent_id = function (n) { return e.is_active() ? e.get_parent(t, n) : i.apply(this, arguments) }; var n = t.setParent; t.setParent = function (i, a) {
        if (!e.is_active()) return n.apply(this, arguments); if (t.isTaskExists(a)) {
          var r = t.getTask(a); i[e.relation_property] = r[e.relation_id_property];
        }
      }, t.attachEvent("onBeforeTaskDisplay", function (i, n) { return e.is_active() && n.type == t.config.types.project && !n.$virtual ? !1 : !0 }), t.attachEvent("onBeforeParse", function () { e.loading = !0 }), t.attachEvent("onTaskLoading", function () { return e.is_active() && (e.loaded--, e.loaded <= 0 && (e.loading = !1, t.eachTask(t.bind(function (e) { this.get_parent(t, e) }, e)), t._sync_order())), !0 }), t.attachEvent("onParse", function () { e.loading = !1, e.loaded = 0 })
    }, get_parent: function (t, e, i) {
      var n = e[this.relation_property]; if (void 0 !== this._groups_pull[n]) return this._groups_pull[n];
      var a = t.config.root_id; return this.loading || (a = this.find_parent(i || t.getTaskByTime(), n, this.relation_id_property, t.config.root_id), this._groups_pull[n] = a), a
    }, find_parent: function (t, e, i, n) { for (var a = 0; a < t.length; a++) { var r = t[a]; if (void 0 !== r[i] && r[i] == e) return r.id } return n }, clear: function () { this._groups_pull = {}, this.relation_property = null, this.group_id = null, this.group_text = null }, is_active: function () { return !!this.relation_property }, generate_sections: function (e, i) {
      for (var n = [], a = 0; a < e.length; a++) {
        var r = t.copy(e[a]);
        r.type = i, r.open = !0, r.$virtual = !0, r.readonly = !0, r[this.relation_id_property] = r[this.group_id], r.text = r[this.group_text], n.push(r)
      } return n
    }, clear_temp_tasks: function (t) { for (var e = 0; e < t.length; e++)t[e].$virtual && (t.splice(e, 1), e--) }, generate_data: function (t, e) { var i = t.getLinks(), n = t.getTaskByTime(); this.clear_temp_tasks(n); var a = []; this.is_active() && e && e.length && (a = this.generate_sections(e, t.config.types.project)); var r = { links: i }; return r.data = a.concat(n), r }, update_settings: function (t, e, i) {
      this.clear(),
      this.relation_property = t, this.group_id = e, this.group_text = i
    }, group_tasks: function (t, e, i, n, a) { this.update_settings(i, n, a); var r = this.generate_data(t, e); this.loaded = r.data.length, t._clear_data(), t.parse(r) }
  }, t._groups.init(t), t.groupBy = function (t) { t = t || {}; var e = t.groups || null, i = t.relation_property || null, n = t.group_id || "key", a = t.group_text || "label"; this._groups.group_tasks(this, e, i, n, a) }
});
//# sourceMappingURL=../sources/ext/dhtmlxgantt_grouping.js.map